import axios from 'axios';
import {CreateAxiosDefaults} from "axios";

const axiosConfig = {
    baseURL: "http://localhost:8080/api",
    timeout: 5000,
} as CreateAxiosDefaults;

const request = axios.create(axiosConfig);

request.interceptors.request.use((config) => {
    return config
}, (error => {
    return Promise.reject(error)
}))

export default request;