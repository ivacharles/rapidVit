import React from "react";
import {Route, Routes} from "react-router-dom";
import "./App.css";
import HomePage from "./pages/HomePage";
import SigninPage from './pages/SigninPage';
import SignUpPage from "./pages/SignUpPage";
import ResetPwdPage from "./pages/ResetPwdPage";
import UserDashboard from "./pages/UserDashboard";
import ListingForm from "./components/ListingForm";

function App() {
    
  return (
      <Routes>
          <Route exact path='/signin' element={<SigninPage/>}/>
          <Route exact path='/signup' element={<SignUpPage/>}/>
          <Route exact path='/reset-pwd' element={<ResetPwdPage/>}/>
          <Route exact path='/user-dashboard' element={<UserDashboard/>}/>
          <Route exact path='/create-listing' element={<ListingForm/>}/>
          <Route exact path='/' element={<HomePage/>}/>
      </Routes>
  );
}

export default App;
