import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import TestAxios from "../../apis/TestAxios"
import { LinijaRow } from "./LinijaRow"
import { Col, FormCheck, FormLabel, Row, Table } from "react-bootstrap"


export const TopMesta = () => {

    const[linije, setLinije] = useState([])
    const [searchParams, setSearchParams] = useState({})
    const[prevoznici, setPrevoznici] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        getLinije()
        getPrevoznici()
    }, [])

    const getLinije = (newPageNo) => {

        const conf = {
            params: {
                ...searchParams,
                top3Mesta: true
            }
        }
        TestAxios.get('/linije', conf)
            .then(res => {
                console.log(res)
                setLinije(res.data)               
            }).catch(error => {
                console.log(error)
                alert('Doslo je do greske')
            })       
    }

    const getPrevoznici = () => {
        TestAxios.get('/prevoznici')
            .then(res => {
                setPrevoznici(res.data)
            }).catch(error => {
                alert('Doslo je do greske!')
            })
    }

    const renderLinije = () => {
        return linije.map((linija,index) => {
            return (
            <LinijaRow key={index} linija={linija} linije={linije} ></LinijaRow>
            )
        })
    }

    return (
        <Col>
            <Row><h1>Linije</h1></Row>
            <br></br>
            <Row><Col>
                <Table striped bordered hover bg="dark" variant="dark" id="linije=table">
                    <thead>
                        <tr>
                            <th>Naziv prevoznika</th>
                            <th>Destinacija</th>
                            <th>Broj mesta</th>
                            <th>Vreme polaska</th>
                            <th>Cena karte</th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderLinije()}
                    </tbody>
                </Table>
            </Col></Row>
        </Col>
    )
}