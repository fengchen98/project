import request from '@/utils/request'

export function getQuestion(token) {
  return request({
    url: '/logistic-test-api/getQuestion',
    method: 'get',
    params: { token }
  })
}
