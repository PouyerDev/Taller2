import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {

    stages: [
        { duration: '1m', target: 1000 },   // Incrementa a 1000 usuarios en 1 minuto
        { duration: '1m', target: 2000 },   // Incrementa a 2000 usuarios en 1 minuto
        { duration: '1m', target: 3000 },   // Incrementa a 3000 usuarios en 1 minuto
        { duration: '1m', target: 4000 },   // Incrementa a 4000 usuarios en 1 minuto
        { duration: '1m', target: 5000 },   // Incrementa a 5000 usuarios en 1 minuto
        { duration: '1m', target: 6000 },   // Incrementa a 6000 usuarios en 1 minuto
        { duration: '1m', target: 7000 },   // Incrementa a 7000 usuarios en 1 minuto
        { duration: '1m', target: 8000 },   // Incrementa a 8000 usuarios en 1 minuto
        { duration: '1m', target: 9000 },   // Incrementa a 9000 usuarios en 1 minuto
        { duration: '1m', target: 10000 },  // Incrementa a 10000 usuarios en 1 minuto
        { duration: '1m', target: 0 },     // Reduce a 0 usuarios en 1 minuto
    ],
    thresholds: {
        http_req_duration: ['p(95)<2000'], // 95% de las solicitudes deben completarse en menos de 2 segundos
    },
};

export default function () {
    let res = http.get('http://localhost:8080/numbers', { timeout: '120s' });
    check(res, {
        'status was 200': (r) => r.status == 200,
    });
    sleep(1); // Espera 1 segundo antes de la próxima solicitud
}