import React, { useState } from "react";
import PopUp from "./PopUp";

const CartSingleProduct = ({ product }) => {
  const [showPopUp, setshowPopUp] = useState(false);

  const handleOrder = (e) => {
    e.stopPropagation();
    setshowPopUp(true);
    setTimeout(() => {
      setshowPopUp(false);
    }, 2000); 
  };

  return (
    <div
      className="p-3"
      style={{
        boxShadow: "5px 0px 5px 0px rgb(192,192,192)",
        height: "400px",
      }}
    >
      <div className="bg-white rounded-lg overflow-hidden shadow-md h-full">
        <div className="h-48 overflow-hidden">
          <img
            src={product.productImageUrl}
            alt="pizza"
            className="w-full h-full object-cover"
            style={{ aspectRatio: "16/9" }}
          />
        </div>
        <div className="p-4">
          <h2 className="text-lg font-bold py-2">{product.productName}</h2>
          <span className="bg-gray-200 py-1 rounded-full text-sm px-4 hover:bg-yellow-400 transition-colors duration-300">
            {product.productSize}
          </span>
          <div className="flex justify-between items-center mt-4">
            <span>₹{`${product.price}.00`}</span>
            <button
              onClick={handleOrder}
              className="bg-green-600 py-1 px-4 rounded-full font-bold "
            >
              Order Now
            </button>
          </div>
        </div>
      </div>
      {showPopUp && <PopUp productData={product}/>}
    </div>
  );
};

export default CartSingleProduct;
