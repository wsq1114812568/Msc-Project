import request from '@/utils/request'

const api_name = `/user`

export default {
    login(userInfo) {
        return request({
            url: `${api_name}/loginByPhone`,
            method: `post`,
            data: userInfo
        })
    },
    loginByName(userInfo) {
        return request({
            url: `${api_name}/loginByName`,
            method: `post`,
            data: userInfo
        })
    },
    register(userInfo){
        return request({
            url:`${api_name}/register`,
            method: `post`,
            data: userInfo
        })
    },
    getUserInfo(){
        return request({
            url:`${api_name}/getUserInfo`,
            method: `get`,
        })
    },
    updateUser(user){
        return request({
            url:`/user/update`,
            method:'post',
            data:user
        })
    },
    findFriendPage(current,limit){
        return request({
            url:`/user/findFriendPage/${current}/${limit}`,
            method:'post'
        })
    },
    findFriend(){
        return request({
            url:`/user/findFriend2`,
            method:'get'
        })
    },
    findFriendRequest(){
        return request({
            url:`/user/friendRequest`,
            method:'get'
        })
    },
    findUnFriend(current,limit,object){
        return request({
            url:`/user/findUnFriendPage/${current}/${limit}`,
            method:'post',
            data:object
        })

    },
    addFriend(friendid){
        return request({
            url:`/user/addFriend/${friendid}`,
            method:'post'
        })
    },
    deleteFriend(friendid){
        return request({
            url:`/user/deleteFriend/${friendid}`,
            method:'delete'
        })
    },
    agreeFriend(friendid){
        return request({
            url:`/user/agree/${friendid}`,
            method:'get'
        })
    },
    findCompetition(userid){
        return request({
            url:`/user/findCompetition/${userid}`,
            method:'get'
        })
    },
    findCompetition2(){
        return request({
            url:`/user/findCompetition2`,
            method:'get'
        })
    },
}
