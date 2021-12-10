import request from '@/utils/request'
const api_name = `/competition`

export default {
    getPageList(current,limit,searchObj) {
        return request({
            url: `${api_name}/findPage3/${current}/${limit}`,
            method: `post`,
            data:searchObj
        })
    },
    findByName(competitionName){
        return request({
            url: `${api_name}/findByName/${competitionName}`,
            method: `get`
        })
    },
    findById(id){
        return request({
            url: `${api_name}/get/${id}`,
            method: `get`
        })
    },
    findUser(competitionid){
        return request({
            url: `${api_name}/findUser/${competitionid}`,
            method: `get`
        })
    },
    findUser2(competitionid){
        return request({
            url: `${api_name}/findUser2/${competitionid}`,
            method: `get`
        })
    },
    attend(competitionid){
        return request({
            url: `${api_name}/attend/${competitionid}`,
            method: `post`
        })
    }
}