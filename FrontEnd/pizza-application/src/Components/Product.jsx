import React, { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import CartContext from "../CartContext";

const Product = ({ data }) => {
  const [isAdding, setIsAdding] = useState(false);
  const {newcart,setNewCart} = useContext(CartContext)

      useEffect(() => {
        const itemExist = newcart.find((element) => element.id === data.id);
        if (itemExist) {
          setIsAdding(true); 
        }
      }, [newcart]); 
      

      const addtoCart = (e, product) => {
        e.preventDefault();
        let cart = [...newcart];
        if (cart.find((element) => element.id === product.id)) {
          const updatedCart = cart.filter((element) => element.id !== product.id);
          setNewCart(updatedCart);
          setIsAdding(false);
        } else {
          let newCart = [...cart, product];
          setNewCart(newCart);
          setIsAdding(true);
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
                  addtoCart(e, data);
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
