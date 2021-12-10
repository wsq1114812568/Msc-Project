import request from '@/utils/request'

const api_name = `/resultDetails`

export default {
    getList(competitionid) {
        return request({
            url: `${api_name}/getlist/${competitionid}`,
            method: `get`,
        })
    },
    getByCompetitionid(competitionid){
        return request({
            url: `${api_name}/getResultDetails/${competitionid}`,
            method: `get`,
        })
    },
    getFinalResult(){
        return request({
            url: `${api_name}/finalResult`,
            method: `get`,
        })

    },
}