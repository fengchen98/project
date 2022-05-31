const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.admin.token,
  // avatar: state => state.admin.avatar,
  // name: state => state.admin.name,
  // signature: state => state.admin.signature,
  // balance: state => state.admin.balance,
  // rebate: state => state.admin.rebate,
  // couponCount: state => state.admin.couponCount,
  // roles: state => state.admin.roles,
  authinfo: state => state.admin.authInfo,
  accountinfo: state => state.admin.accountInfo,
  baseinfo: state => state.admin.baseInfo
}
export default getters
