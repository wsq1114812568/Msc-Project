import request from '@/utils/request'

export default{
    getClubList(current,limit){
        return request({
            url:`/club/findPage/${current}/${limit}`,
            method:'post',
        })
    },
    getList(){
        return request({
            url:`/club/getList`,
            method:'get',
        })
    },

    deleteClub(id){
        return request({
            url:`/club/delete/${id}`,
            method:'delete',
        })
    },

    batchDelete(idList){
        return request({
            url:`/club/batchRemove`,
            method:'delete',
            data:idList
        })
    },

    addClub(club){
        return request({
            url:`/club/add`,
            method:'post',
            data:club//json-->requestbody
        })
    },

    getClub(clubid){
        return request({
            url:`/club/get/${clubid}`,
            method:'get',
        })
    },

   updateClub(club){
        return request({
            url:`/club/update`,
            method:'post',
            data:club
        })
    },
    findCompetition(clubid){
        return request({
            url:`/club/findCompetition/${clubid}`,
            method:'get',
        })

    },

    //tables

   showTables(clubid){
        return request({
            url:`/table/show/${clubid}`,
            method:'get',
        })
    },
    addTables(table){
        return request({
            url:`/table/add`,
            method:'post',
            data:table
        })
    },
}



