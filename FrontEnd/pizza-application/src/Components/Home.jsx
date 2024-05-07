import React from "react";
import Products from "./Products";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const navigate = useNavigate();

  const handleOrderNow = () => {
    navigate("/products");
  };

  return (
    <>
      <div className="hero py-6">
        <div className="container mx-auto flex items-center justify-between">
          <div className="w-1/2 px-6">
            <h6 className="text-lg">
              <em>Are you hungry?</em>
            </h6>
            <h1 className="text-3xl md:text-6xl font-bold">Don't wait !</h1>
            <button
              className="px-6 py-2 rounded-full text-white font-bold mt-4 bg-yellow-500 hover:bg-yellow-600"
              onClick={handleOrderNow}
            >
              Order Now
            </button>
          </div>
          <div className="w-1/2">
            <img className="w-4/5" src="/images/pizza.png" alt="pizza" />
          </div>
        </div>
      </div>
      <hr className="border-t border-yellow-600 my-4 ml-20 mr-14" />
      {/* <div className="pb-24">
        <Products />
      </div> */}
      <Products />
    </>
  );
};

export default Home;
