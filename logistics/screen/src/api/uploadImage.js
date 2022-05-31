import request from '@/utils/request'

export function uploadImageDefault(data, type) {
  return request({
    url: `/webapp/upload/upload/${type}`,
    method: 'post',
    data
  })
}

// 批量上传, 存储路径classpath: /static/uploadImages/type/id/apply
export function uploadProductImageDefault(data, type, id, apply) {
  return request({
    url: `/webapp/upload/batchUpload/${type}/${id}/${apply}`,
    headers: { 'Content-Type': 'multipart/form-data' },
    method: 'post',
    data: data
  })
}
