import React, {Fragment, useEffect, useState} from "react";
import {Col, Container, Row} from "react-bootstrap";
import CardBanner from "../components/CardBanner";
import shopIMG from "../images/sale.jpg"
import HousingIMG from "../images/housing.jpg"
import ServiceIMG from "../images/suporte-business-local.jpg"
import JobIMG from "../images/job.jpg"
import CommunityIMG from "../images/ki-program-ki-gen-jodia.jpg"
import LatestPostingCard from "../components/LatestPostingCard";
import Menu from "../components/Menu";
import SubMenu from "../components/SubMenu";

function HomePage() {
    const [listings, setListings] = useState(null);
    const [error, setError] = useState(null);
    useEffect(() => {
        console.log("useeffect");
        // axios("http://localhost:8080/listing/all")
        //     .then((res) => {
        //             console.log("--------------------Axios-data---------------------");
        //             setListings(res.data);
        //             console.log(listings);
        //     })
        //     .catch((error) => {
        //         console.log("-----------------------Axios-error---------------------");
        //         setError(error.toJSON());
        //         if (error.response) {
        //             // The request was made and the server responded with a status code
        //             // that falls out of the range of 2xx
        //             console.log(error.response.data);
        //             console.log(error.response.status);
        //             console.log(error.response.headers);
        //         } else if (error.request) {
        //             // The request was made but no response was received
        //             // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
        //             // http.ClientRequest in node.js
        //             console.log(error.request);
        //         } else {
        //             // Something happened in setting up the request that triggered an Error
        //             console.log('Error: ', error.message);
        //         }
        //         console.log(error.config);
        //     });
    }, []);
    return (
        <Fragment>
            <Menu/>
            <SubMenu/>
            <Container  className={"mt-3 "}>
                <ShowCase/>
                <LatestPost/>
            </Container>
        </Fragment>
    );
}

function ShowCase(){

    return(
        <Row className={"g-2"}>
            <Col>
                <CardBanner bannerIMG={shopIMG} bannerPage={"shop"} bannerTitle={"klike pou ou ht paw"}
                            bannerDesc={"wap jwen tout sa ou bezoin la"} bannerBtn={"HT PAW"}/>
            </Col>
            <Col>
                <CardBanner bannerIMG={HousingIMG} bannerPage={"housing"} bannerTitle={"Kay - chanm - Lokal"}
                            bannerDesc={"Ou vle loue oubyen chahce kay"} bannerBtn={"CLIKE LA"}/>
            </Col>
            <Col>
                <CardBanner bannerIMG={ServiceIMG} bannerPage={"service"} bannerTitle={"Suporte Buzinis Lokal"}
                            bannerDesc={"Chache sevis ke ou bezoin an la"} bannerBtn={"Buzinis Lokal"}/>
            </Col>
            <Col>
                <CardBanner bannerIMG={JobIMG} bannerPage={"job"} bannerTitle={"Wap chache travay"}
                            bannerDesc={"ou nan bon kote a pou jwen job ou a"} bannerBtn={"CLIKE LA"}/>
            </Col>
            <Col>
                <CardBanner bannerIMG={CommunityIMG} bannerPage={"event"} bannerTitle={"Plezi a koumanse"}
                            bannerDesc={"An nou al pran plezi nou tande"} bannerBtn={"CLIKE LA"}/>
            </Col>
        </Row>
    );

}


function LatestPost() {
    return (
        <Container fluid className={"mt-5"}>
            <Row className="my-4 border-bottom text-black fs-5 fw-bolder text-nowrap">Today's Latest Listings</Row>
            <Row >
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
                <Col className={"py-2"}><LatestPostingCard bannerIMG={HousingIMG} bannerTitle={"Kay - chanm - Lokal"} bannerDesc={"Last updated 3 mins ago"} /></Col>
            </Row>
        </Container>
    );
}

export default HomePage;
