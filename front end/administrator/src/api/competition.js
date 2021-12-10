import request from '@/utils/request'

export default{
    getList(current,limit){
        return request({
            url:`/competition/findPage/${current}/${limit}`,
            method:'post',
        })
    },
    getList2(current,limit){
        return request({
            url:`/competition/findPage2/${current}/${limit}`,
            method:'post',
        })
    },

    delete(id){
        return request({
            url:`/competition/delete/${id}`,
            method:'delete',
        })
    },

    batchDelete(idList){
        return request({
            url:`/competition/batchRemove`,
            method:'delete',
            data:idList
        })
    },

    add(competition){
        return request({
            url:`/competition/add`,
            method:'post',
            data:competition//json-->requestbody
        })
    },

    get(id){
        return request({
            url:`/competition/get/${id}`,
            method:'get',
        })
    },

   update(competition){
        return request({
            url:`/competition/update`,
            method:'post',
            data:competition
        })
    },
    changeStatus(competitionid){
        return request({
            url:`/competition/changeStatus/${competitionid}`,
            method:'get',
        })

    },
    findUser(competitionid){
        return request({
            url:`/competition/findUser/${competitionid}`,
            method:'get',
        })
    }



}