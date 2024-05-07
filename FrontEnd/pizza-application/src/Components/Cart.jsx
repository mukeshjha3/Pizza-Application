import React, { useContext } from 'react'
import CartContext from '../CartContext'
import CartSingleProduct from './CartSngleProduct';


const Cart = () => {

  const {newcart,setNewCart} = useContext(CartContext);
  // [{},{}]

  return (
    <div>
      <h1 className="text-lg font-extrabold my-8 text-center text-yellow-700">Your Cart</h1>
      {newcart.length!==0 ? (
        <div className="grid grid-cols-5 gap-8">{newcart.map((product) => (
        // <CartSingleProduct key={product.id} {...product} />
        <CartSingleProduct key={product.id} product={product} />
      ))} </div>):( <h1 className='text-lg font-extrabold my-8 text-center text-orange-500'>No items has been been added to your cart</h1>)}
   
    </div>
  );
};

export default Cart;
