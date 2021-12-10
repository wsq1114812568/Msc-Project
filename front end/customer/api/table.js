import request from '@/utils/request'
const api_name = `/table`

export default {
    showTables(clubid) {
        return request({
            url: `${api_name}/show2/${clubid}`,
            method: `get`,
        })
    },
    book(table){
        return request({
            url: `${api_name}/book`,
            method: `post`,
            data:table
        })
    },
    showTablesBook(){
        return request({
            url: `${api_name}/showTablesBook`,
            method: `get`,
        })
    }
}
