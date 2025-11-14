package main.java.heshiranje.zadaci.ispit2021;

public class zadacaTest {
    public static void main(String[] args) {
        zadaca zadcice = new zadaca();

        // Test Case 1
        String input1 = "5\nAnalgin Glavobolka 80\nDaleron Glavobolka 90\nLineks Bolki_vo_stomak 150\nSpazmeks Bolki_vo_stomak 150\nLoratadin Alergija 150\nGlavobolka\n";
        if (zadcice.testFunction(input1).equals("Analgin")) {
            System.out.println("Test Case 1 Passed");
        } else {
            System.out.println("Test Case 1 Failed");
        }

        // Test Case 2
        String input2 = "5\nLineks Bolki_vo_stomak 150\nAnalgin Glavobolka 80\nSpazmeks Bolki_vo_stomak 150\nLoratadin Alergija 150\nDaleron Glavobolka 90\nBolki_vo_stomak\n";
        if (zadcice.testFunction(input2).equals("Lineks")) {
            System.out.println("Test Case 2 Passed");
        } else {
            System.out.println("Test Case 2 Failed");
        }

        // Test Case 3
        String input3 = "5\nLineks Bolki_vo_stomak 150\nAnalgin Glavobolka 80\nSpazmeks Bolki_vo_stomak 150\nLoratadin Alergija 150\nDaleron Glavobolka 90\nGrip\n";
        if (zadcice.testFunction(input3).equals("Nema lek za baranata namena vo magacin.")) {
            System.out.println("Test Case 3 Passed");
        } else {
            System.out.println("Test Case 3 Failed");
        }

        // Test Case 4
        String input4 = "10\nVibramicin Temperatura 300\nFerveks Temperatura 100\nAnalgin Glavobolki 80\nMagnezium Bolki_vo_muskilite 200\nKalcium Bolki_vo_koskite 100\nInsulin Sheker_vo_krvta 500\nDaleron Glavobolka 90\nKetonal Bolki_vo_muskulite 200\nApirin Glavobolka 90\nLoratadin Alergija 100\nTemperatura\n";
        if (zadcice.testFunction(input4).equals("Ferveks")) {
            System.out.println("Test Case 4 Passed");
        } else {
            System.out.println("Test Case 4 Failed");
        }

        // Test Case 5
        String input5 = "100\nAspirin Nastinka 100\nDaleron Virus 90\nAmoksiklav Temperatura 90\nFerveks Virus 100\nVibramicin Virus 80\nDaleron Nastinka 90\nFerveks Nastinka 100\nAmoksiklav Temperatura 120\nFerveks Grip 100\nAspirin Nastinka 100\nAmoksiklav Temperatura 200\nAnalgin Temperatura 200\nAspirin Virus 250\nAspirin Grip 80\nDaleron Nastinka 100\nAmoksiklav Grip 100\nAnalgin Virus 200\nAnalgin Grip 120\nAmoksiklav Virus 80\nFerveks Temperatura 90\nAmoksiklav Virus 80\nAnalgin Virus 90\nFerveks Grip 100\nDaleron Temperatura 90\nFerveks Temperatura 120\nDaleron Virus 150\nDaleron Virus 200\nAnalgin Grip 200\nAnalgin Grip 90\nFerveks Virus 90\nFerveks Nastinka 80\nFerveks Temperatura 200\nDaleron Grip 80\nAspirin Virus 80\nAspirin Nastinka 90\nFerveks Grip 80\nFerveks Virus 80\nFerveks Temperatura 250\nFerveks Nastinka 80\nVibramicin Temperatura 150\nDaleron Virus 100\nDaleron Virus 80\nAmoksiklav Grip 100\nAmoksiklav Grip 150\nDaleron Grip 80\nDaleron Virus 250\nAspirin Virus 90\nFerveks Virus 100\nFerveks Virus 250\nAnalgin Virus 90\nAspirin Virus 200\nAspirin Temperatura 100\nAmoksiklav Virus 250\nVibramicin Nastinka 100\nVibramicin Virus 90\nVibramicin Virus 150\nDaleron Virus 120\nAnalgin Grip 90\nAnalgin Nastinka 250\nDaleron Grip 100\nAspirin Temperatura 90\nAspirin Virus 200\nVibramicin Nastinka 80\nVibramicin Virus 120\nDaleron Virus 100\nAnalgin Nastinka 150\nDaleron Nastinka 200\nFerveks Nastinka 100\nFerveks Grip 150\nAmoksiklav Grip 100\nAspirin Grip 100\nDaleron Temperatura 100\nDaleron Nastinka 150\nAspirin Grip 100\nVibramicin Grip 250\nDaleron Grip 80\nFerveks Temperatura 80\nVibramicin Grip 150\nFerveks Nastinka 250\nVibramicin Grip 120\nAspirin Temperatura 250\nVibramicin Temperatura 120\nAnalgin Nastinka 90\nAnalgin Virus 200\nFerveks Virus 150\nAspirin Virus 100\nDaleron Grip 120\nDaleron Temperatura 200\nFerveks Nastinka 150\nAmoksiklav Grip 250\nDaleron Virus 100\nAmoksiklav Virus 150\nFerveks Grip 150\nDaleron Temperatura 150\nAspirin Nastinka 90\nFerveks Virus 150\nFerveks Virus 120\nDaleron Virus 100\nAnalgin Virus 100\nVibramicin Grip 100\nGrip\n";
        if (zadcice.testFunction(input5).equals("Aspirin")) {
            System.out.println("Test Case 5 Passed");
        } else {
            System.out.println("Test Case 5 Failed");
        }
    }
}
