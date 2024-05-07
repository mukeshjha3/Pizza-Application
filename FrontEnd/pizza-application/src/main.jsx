import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Navbar from "./Components/Navbar.jsx";
import Footer from "./Components/Footer.jsx";
import Products from "./Components/Products.jsx";
import Cart from "./Components/Cart.jsx";
import Home from "./Components/Home.jsx";
import SingleProduct from "./Components/SingleProduct.jsx";
import Error from "./Components/Error.jsx";
import CartContext from "./CartContext.js";
import App from "./App.jsx";

ReactDOM.createRoot(document.getElementById("root")).render(<App></App>);
