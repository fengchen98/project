import { Message } from 'element-ui'

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validInput(str, minlength = 8, maxlength = 16) {
  const reg = /[a-zA-Z0-9\-\_\@\.]{8,16}/
  if (str.length >= minlength && str.length <= maxlength && reg.test(str)) {
    return true
  } else {
    Message.error(`请确保 ${str} 长度在8-16位,且由字母、数字和标点符号组成`)
    return false
  }
}

export function validEmail(email) {
  const reg = /([0-9a-zA-Z]+)@([a-z0-9A-Z]+).([a-zA-Z]+)/
  return reg.test(email)
}

export function validPhone(phone) {
  const reg = /1[0-9]{10}/
  return reg.test(phone)
}

export function validIdNumber(idNumber) {
  const reg = /[0-9]{18}/
  return reg.test(idNumber)
}

export function validSamePassword(str1, str2, minLength = 8, maxlength = 16) {
  return str1.length >= minLength && str2.length >= minLength && str1 === str2
}
