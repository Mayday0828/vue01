import axios from 'axios'


const request = axios.create({
    baseURL: 'http://localhost:7070',   // 后端的接口地址  ip:port
    timeout: 30000
})

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    config.headers['token'] = 'token'  // 设置请求头

    return config
}, error => {
    console.error('request error: ' + error)
    return Promise.reject(error)
});


request.interceptors.response.use(
    response => {
        let res = response.data;

        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.error('response error: ' + error) // for debug
        return Promise.reject(error)
    }
)


export default request
