import request from '@/utils/request'

const api_name = `/message`

export default {
    sendMessage(friendid,object) {
        return request({
            url: `${api_name}/sendMessage/${friendid}`,
            method: `post`,
            data: object
        })
    },
    showMessage(){
        return request({
            url: `${api_name}/showMessage`,
            method: `get`,
        })
    },
    deleteMessage(id){
        return request({
            url: `${api_name}/deleteMessage/${id}`,
            method: `delete`,
        })
    }
}