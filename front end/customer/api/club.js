import request from '@/utils/request'
const api_name = `/club`

export default {
    getClubList() {
        return request({
            url: `${api_name}/getList`,
            method: `get`,
        })
    },
    getClub(id){
        return request({
            url: `${api_name}/get/${id}`,
            method: `get`,
        })
    },
    findCompetition(clubid){
        return request({
            url: `${api_name}/findCompetition/${clubid}`,
            method: `get`,
        })
    },
    
    
    
}