import request from '@/utils/request'

export default{
    getUserList(current,limit,object){
        return request({
            url:`/user/findPage/${current}/${limit}`,
            method:'post',
            data:object
        })
    },

    deleteUser(id){
        return request({
            url:`/user/delete/${id}`,
            method:'delete',
        })
    },

    batchDelete(idList){
        return request({
            url:`/user/batchRemove`,
            method:'delete',
            data:idList//json-->requestbody
        })
    },

    addUser(user){
        return request({
            url:`/user/add`,
            method:'post',
            data:user//json-->requestbody
        })
    },

    getUser(id){
        return request({
            url:`/user/get/${id}`,
            method:'get',
        })
    },

   updateUser(user){
        return request({
            url:`/user/update`,
            method:'post',
            data:user
        })
    },
    findFriend(userid){
        return request({
            url:`/user/findFriend/${userid}`,
            method:'get'
        })

    },
    findCompetition(userid){
        return request({
            url:`/user/findCompetition/${userid}`,
            method:'get'
        })

    },



}