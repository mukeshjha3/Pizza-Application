import { RouterProvider, createBrowserRouter } from "react-router-dom";
import "./App.css";
import Navbar from "./Components/Navbar";
import Home from "./Components/Home";
import Footer from "./Components/Footer";
import Products from "./Components/Products";
import Cart from "./Components/Cart";
import SingleProduct from "./Components/SingleProduct";
import Error from "./Components/Error";
import CartContext from "./CartContext";
import { useEffect, useState } from "react";

function App() {
  const [newcart, setNewCart] = useState([]);

  useEffect(() => {
    let cart = JSON.parse(localStorage.getItem("cart"));
    if (cart && cart.length !== 0) {
      setNewCart(cart);
    }
  }, []);

  useEffect(() => {
    localStorage.setItem("cart", JSON.stringify(newcart));
  }, [newcart]);

  
  const router = createBrowserRouter([
    {
      path: "/",
      element: (
        <>
          <Navbar />
          <Home /> <Footer />
        </>
      ),
    },
    {
      path: "/products",
      element: (
        <>
          <Navbar/>
          <Products/> <Footer/>
        </>
      ),
    },

    {
      path: "/cart",
      element: (
        <>
          <Navbar />
          <Cart /> <Footer />
        </>
      ),
    },

    {
      path: "/products/:id",
      element: (
        <>
          <Navbar />
          <SingleProduct />
          <Footer />
        </>
      ),
    },

    {
      path: "*",
      element: <Error />,
    },
  ]);

  return (
  <CartContext.Provider value= {{newcart,setNewCart}}>
      <RouterProvider router={router} />
    </CartContext.Provider>
  );
}

export default App;
