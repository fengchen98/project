import request from '@/utils/request'

export function save(data) {
  return request({
    url: '/webapp/concat/save',
    method: 'post',
    data: data
  })
}

export function get() {
  return request({
    url: '/webapp/concat/get',
    method: 'GET'
  })
}

export function query(id) {
  return request({
    url: `/webapp/concat/query/${id}`,
    method: 'GET'
  })
}

export function deal(id) {
  return request({
    url: `/webapp/concat/deal/${id}`,
    method: 'GET'
  })
}

export function search(text) {
  return request({
    url: `/webapp/concat/search?search=${text}`,
    method: 'GET'
  })
}

export function remove(id) {
  return request({
    url: `/webapp/concat/remove/${id}`,
    method: 'POST'
  })
}
