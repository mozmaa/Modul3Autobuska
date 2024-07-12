import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import TestAxios from "../../apis/TestAxios"
import { FormSelect, Row } from "react-bootstrap"
import { Col } from "react-bootstrap"
import { Form } from "react-bootstrap"
import { Button } from "react-bootstrap"

export const AddLinija = () => {

    const [prevoznici, setPrevoznici] = useState([])
    const [novaLinija, setNovaLinija] = useState([])
    const navigate = useNavigate()

    const getPrevoznici = () => {
        TestAxios.get('/prevoznici')
            .then(res => {
                setPrevoznici(res.data)
            }).catch(error => {
                alert('Doslo je do greske!')
            })
    }

    useEffect(() => {
        getPrevoznici()
    }, [])

    const handleChange = (e) => {
        
        setNovaLinija({ ...novaLinija, [e.target.name]: e.target.value })
    }

    const dodavanjeLinija = () => {
        TestAxios.post('/linije', novaLinija)
            .then(res => {
                alert('Uspesno dodavanje')
                navigate('/linije')
            }).catch(error => {
                alert('Doslo je do greske')
            })
    }

    return (
        <Row className="justify-content-center">
            <Col md={8}>
                <h1>Dodavanje</h1>
                <Form.Group>
                    <Form.Label>Broj mesta</Form.Label>
                    <Form.Control type='number' name='brojMesta' min={1} max={80} onChange={(e) => handleChange(e)} ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Cena karte</Form.Label>
                    <Form.Control type='number' name='cenaKarte' min={10} onChange={(e) => handleChange(e)} ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Destinacija</Form.Label>
                    <Form.Control type='text' name='destinacija' onChange={(e) => handleChange(e)} ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Vreme polaska</Form.Label>
                    <Form.Control type='time' name='vremePolaska' onChange={(e) => handleChange(e)} ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Prevoznik</Form.Label>
                    <FormSelect name="prevoznikId" onChange={(e) => handleChange(e)}>
                        <option value=''>--</option>
                        {prevoznici.map((prevoznik, index) => {
                            return (
                                <option key={index} value={prevoznik.id}>{prevoznik.naziv}</option>
                            )
                        })
                        }
                    </FormSelect>
                </Form.Group>
                <Button style={{ marginTop: 10, marginBottom: 10 }} onClick={dodavanjeLinija}>Dodaj</Button>
            </Col>
        </Row>
    )
}