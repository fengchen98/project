import request from '@/utils/request'

export function getNews(token) {
  return request({
    url: '/logistic-test-api/getNews',
    method: 'get',
    params: { token }
  })
}
