/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'mainPurple': '#360750',
        'mainOrange': '#F46D22',
        'darkBlue': '#1B2028',
        'lightBlue': '#413E49',
        'textWhite': '#FFF',
        'textDark': '#292D32',
        'lightGray': '#737373',
        'logoBlue': '#406cfc'
      }
    },
  },
  plugins: [],
}

