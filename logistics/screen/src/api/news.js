import request from '@/utils/request'

export function query(id) {
  return request({
    url: `/webapp/news/query/${id}`,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/webapp/news/save',
    method: 'post',
    data
  })
}

export function uploadImage(data) {
  return request({
    url: '/webapp/news/upload/news',
    method: 'post',
    data
  })
}

export function titleList() {
  return request({
    url: '/webapp/news/titleList',
    method: 'get'
  })
}

export function titleListByType(type) {
  return request({
    url: `/webapp/news/titleListByType/${type}`,
    method: 'get'
  })
}

export function list() {
  return request({
    url: '/webapp/news/list',
    method: 'get'
  })
}

export function alllist() {
  return request({
    url: '/webapp/news/alllist',
    method: 'get'
  })
}

export function ListByType(type) {
  return request({
    url: `/webapp/news/ListByType/${type}`,
    method: 'get'
  })
}

export function changeStatus(type, id) {
  return request({
    url: `/webapp/news/changeStatus/${type}/${id}`,
    method: 'post'
  })
}

export function remove(id) {
  return request({
    url: `/webapp/news/remove/${id}`,
    method: 'post'
  })
}

export function update(data) {
  return request({
    url: `/webapp/news/update`,
    method: 'post',
    data
  })
}
