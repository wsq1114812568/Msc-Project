import request from '@/utils/request'

export default{
    getUserList(current,limit){
        return request({
            url:`/admin/findPage/${current}/${limit}`,
            method:'post',
        })
    },

    deleteUser(id){
        return request({
            url:`/admin/delete/${id}`,
            method:'delete',
        })
    },

    batchDelete(idList){
        return request({
            url:`/admin/batchRemove`,
            method:'delete',
            data:idList//json-->requestbody
        })
    },

    addUser(admin){
        return request({
            url:`/admin/add`,
            method:'post',
            data:admin//json-->requestbody
        })
    },

    getUser(id){
        return request({
            url:`/admin/get/${id}`,
            method:'get',
        })
    },

   updateUser(user){
        return request({
            url:`/admin/update`,
            method:'post',
            data:user
        })
    },



}