//引入axios
import axios from 'axios';
//设置请求数据api接口和imgPath根路径
const axiosUtil={
  apiPath:'/apis',
  imgPath:''
}

//声明axios发送请求的方法ajax,设置发送请求的根路径为apiPath,
// 也就是说不管什么地方调用axiosUtil.ajax方法
// 路径都为axiosUtil.apiPath+req路径
axiosUtil.ajax=axios.create({
  baseURL:axiosUtil.apiPath
});

//声明axios发送请求的方法ajax返回结果后的拦截器
axiosUtil.ajax.interceptors.response.use(res=>{
  return res.data;
})

//导出axiosUtil
export default axiosUtil;
