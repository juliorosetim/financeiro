/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        sans: ["Roboto", "sans-serif"],
      },
      colors: {
        verde: {
          50: "#a6bf91",
          100: "#5f9982"
        },
        roxo: {
          50: "#78576b",
          100: "#400428"
        },
        amarela: {
          50: "#f4e196"
        }
      }
    },
  },
  plugins: [],
}

