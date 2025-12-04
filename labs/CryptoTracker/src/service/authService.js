// services/authService.js
import { collection, getDocs, query, where } from "firebase/firestore";
import { db } from "../firebase/firebase";

export const loginUser = async (identifier, password) => {
  const usersRef = collection(db, "users");

  // Прво барај по email
  let q = query(usersRef, where("email", "==", identifier));
  let querySnapshot = await getDocs(q);

  // Ако нема резултат, барај по username
  if (querySnapshot.empty) {
    q = query(usersRef, where("username", "==", identifier));
    querySnapshot = await getDocs(q);
  }

  if (!querySnapshot.empty) {
    const userDoc = querySnapshot.docs[0];
    const userData = userDoc.data();

    if (userData.password === password) {
      return {
        success: true,
        user: {
          id: userDoc.id,
          fullName: userData.fullName,
          username: userData.username,
          email: userData.email,
        },
      };
    } else {
      return { success: false, message: "Incorrect password" };
    }
  } else {
    return { success: false, message: "User not found" };
  }
};
