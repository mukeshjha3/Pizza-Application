import React from 'react';

const PopUp = ({productData}) => {
  return (
    <div className="fixed inset-0 flex items-center justify-center backdrop-filter backdrop-blur-lg bg-opacity-50">
      <div className="bg-slate-300 p-16 rounded-lg text-center">
      <p className="text-2xl font-bold  text-indigo-500">{productData.productName}</p>
        <p className="text-4xl font-bold text-orange-400"> Has Order Placed Successfully!</p>
      </div>
    </div>
  );
};

export default PopUp;
