import request from '@/utils/request'

export function query(id) {
  return request({
    url: `/webapp/problem/query/${id}`,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/webapp/problem/save',
    method: 'post',
    data
  })
}

export function uploadImage(data) {
  return request({
    url: '/webapp/problem/upload/problem',
    method: 'post',
    data
  })
}

export function titleList() {
  return request({
    url: '/webapp/problem/titleList',
    method: 'get'
  })
}

export function list() {
  return request({
    url: '/webapp/problem/list',
    method: 'get'
  })
}

export function alllist() {
  return request({
    url: '/webapp/problem/alllist',
    method: 'get'
  })
}

export function ListByType(type) {
  return request({
    url: `/webapp/problem/ListByType/${type}`,
    method: 'get'
  })
}

export function changeStatus(type, id) {
  return request({
    url: `/webapp/problem/changeStatus/${type}/${id}`,
    method: 'post'
  })
}

export function remove(id) {
  return request({
    url: `/webapp/problem/remove/${id}`,
    method: 'post'
  })
}

export function update(data) {
  return request({
    url: `/webapp/problem/update`,
    method: 'post',
    data
  })
}
