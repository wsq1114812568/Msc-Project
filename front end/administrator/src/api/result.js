import request from '@/utils/request'

export default{
    getResult(competitionid){
        return request({
            url:`/result/getResult/${competitionid}`,
            method:'get',
        })
    },
    addOrUpdate(result){
        return request({
            url:`/result/addOrUpdate`,
            method:'post',
            data:result
        })
    },
    getResultDetails(competitionid){
        return request({
            url:`/resultDetails/getResultDetails/${competitionid}`,
            method:'get',
        })
    },
    add(resultDetails){
        return request({
            url:`/resultDetails/add`,
            method:'post',
            data:resultDetails
        })
    },

}