import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import CartContext from "../CartContext";

const BASEURL = "http://localhost:8080/getSinglePizza/";

const SingleProduct = () => {
  const [product, setProduct] = useState({});
  const [isAdding, setIsAdding] = useState(false);
  const params = useParams();
  const navigate = useNavigate();
  const { newcart, setNewCart } = useContext(CartContext);

  useEffect(() => {
    const fetchSingleProduct = async () => {
      try {
        const jsonResponse = await fetch(`${BASEURL}${params.id}`);
        const response = await jsonResponse.json();
        setProduct(response);
      } catch (error) {
        console.error("Error fetching single product:", error);
        // Redirect to error page or display error message
        navigate("/error");
      }
    };
    fetchSingleProduct();
  }, [params.id]);

  useEffect(() => {
  
    const itemExist = newcart.find((element) => element.id === product.id);
    if(itemExist){
    console.log("This item already exist : ",itemExist);
      setIsAdding(true);
    }
  }, [product.id, newcart]);

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
    <div className="container mx-auto mt-12 mb-10">
      <button
        type="button"
        className="text-white bg-gray-800 hover:bg-gray-950 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-full text-sm px-9 py-2.5 me-2 mb-2 ml-9 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700"
        onClick={() => navigate(-1)}
      >
        Back
      </button>
      <div className="w-1/5 p-4 ml-8">
        <div className="rounded-lg overflow-hidden shadow-md bg-white">
          <img
            src={product.productImageUrl}
            alt="pizza"
            className="w-full h-64 object-cover"
            style={{ objectFit: "cover" }}
          />
          <div className="p-6">
            <h1 className="text-xl font-bold mb-2">{product.productName}</h1>
            <p className="text-md mb-4">{product.productSize}</p>
            <p className="text-lg font-bold">₹ {product.price}</p>
            <button
              className={`${
                isAdding ? "bg-green-500" : "bg-yellow-500"
              } py-1 px-4 rounded-full font-bold`}
              onClick={(e) => addtoCart(e, product)}
            >
              {isAdding ? "Added to Cart" : "Add to Cart"}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SingleProduct;
