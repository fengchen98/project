import request from '@/utils/request'

export function sendEmail(data) {
  return request({
    url: '/user/findpassword',
    method: 'post',
    data
  })
}

export function validateEmailCode(data) {
  return request({
    url: '/user/validateEmailCode',
    method: 'post',
    data
  })
}
