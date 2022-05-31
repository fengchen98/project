import request from '@/utils/request'

export function dimQuery(params) {
  let str = '?'
  for (const i in params) {
    str += `${i}=${params[i]}&`
  }
  str = str.slice(0, -1)
  return request({
    url: `/user/dimQuery${str}`,
    method: 'GET'
  })
}

export function getUserList() {
  return request({
    url: '/user/userList',
    method: 'get'
  })
}

export function register(data) {
  return request({
    url: '/user/register',
    method: 'post',
    data
  })
}

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/user/getinfo',
    method: 'post'
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function update(data) {
  return request({
    url: '/user/update',
    method: 'post',
    data
  })
}

export function changePasswordByEmail(data) {
  return request({
    url: '/user/changePasswordByEmail',
    method: 'post',
    data
  })
}
