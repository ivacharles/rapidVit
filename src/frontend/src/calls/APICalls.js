import React from "react";

const checkResponseStatus = response => {
    if (response.ok){
        return response;
    }
    // convert non-2xx HTTP responses into errors:
    const error = new Error(response.statusText);
    error.response = response;
    return Promise.reject(error);
}

export const getAllListings = () => {
  fetch("listing/all")
      .then(checkResponseStatus);
}