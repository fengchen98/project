import request from '@/utils/request'

export function get(user_id) {
  return request({
    url: `/webapp/cid/get/${user_id}`,
    method: 'POST'
  })
}

export function save(data) {
  return request({
    url: '/webapp/cid/save',
    method: 'POST',
    data
  })
}

export function confirm(user_id) {
  return request({
    url: `/webapp/cid/confirm/${user_id}`,
    method: 'GET'
  })
}

export function upload(data, orient) {
  return request({
    url: `/webapp/cid/upload/${orient}`,
    method: 'POST',
    data
  })
}
