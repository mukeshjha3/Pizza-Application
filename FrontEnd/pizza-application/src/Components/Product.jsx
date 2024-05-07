import React, { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import CartContext from "../CartContext";

const Product = ({ data }) => {
  const [isAdding, setIsAdding] = useState(false);
      const {newcart,setNewCart} = useContext(CartContext)

      useEffect(() => {
        // Check if the item is already in the cart
        const cart = JSON.parse(localStorage.getItem("cart")) || [...newcart];
        const itemExist = cart.find((element) => element.id === data.id);
        if (itemExist) {
          setIsAdding(true); // Update button state if item exists in cart
          console.log(cart);
        }
      }, [newcart]); // Include newcart in the dependency array
      

  const addToCart = (e, data) => {
    e.preventDefault();
    let cart = JSON.parse(localStorage.getItem("cart")) || [...newcart];
    let itemExist = cart.find((element) => element.id === data.id);
    if (itemExist) {
      const updatedCart=cart.filter((element) => element.id !== data.id);
      localStorage.setItem("cart", JSON.stringify(updatedCart));
      console.log("item removed from localstorage")
      setIsAdding(false);
      setNewCart(updatedCart);
      return; // Exit early if item already exists in cart
    } else {
      let newCart = [...cart, data];
      localStorage.setItem("cart", JSON.stringify(newCart));
      setIsAdding(true); // Update button state after adding to cart
      setNewCart(newCart);
    }
  };

  return (
    <Link to={`/products/${data.id}`}>
      <div
        className="p-3"
        style={{
          boxShadow: "5px 0px 5px 0px rgb(192,192,192)",
          height: "400px",
        }}
      >
        <div className="bg-white rounded-lg overflow-hidden shadow-md h-full">
          <div className="relative h-48 overflow-hidden">
            <img
              src={data.productImageUrl}
              alt="pizza"
              className="absolute top-0 left-0 w-full h-full object-cover"
              style={{ aspectRatio: "16/9" }}
            />
          </div>
          <div className="p-4">
            <h2 className="text-lg font-bold py-2">{data.productName}</h2>
            <span className="bg-gray-200 py-1 rounded-full text-sm px-4 hover:bg-yellow-400 transition-colors duration-300">
              {data.productSize}
            </span>
            <div className="flex justify-between items-center mt-4">
              <span>₹{`${data.price}.00`}</span>
              <button
                onClick={(e) => {
                  addToCart(e, data);
                }}
                className={`${
                  isAdding ? "bg-green-500" : "bg-yellow-500"
                } py-1 px-4 rounded-full font-bold`}
    
              >
                {isAdding ? "Added to Cart" : "Add to Cart"}
              </button>
            </div>
          </div>
        </div>
      </div>
    </Link>
  );
};

export default Product;
