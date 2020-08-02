<template>
  <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px" class="demo-ruleForm login-container" style="position:relative;width:565px;">
    <h3 style="color: #20A0FF;position: absolute;left: 0;bottom: -60px;">© Copyright Reserved 2019</h3>
    <h1 style="color: #20A0FF;position: absolute;left: 0;top: -80px;">瑞达检测服务平台</h1>
    <div style='display:inline-block;width:350px;vertical-align:top;'>
        <el-form-item prop="account">
          <el-input type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="输入账号"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
          <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="输入密码"></el-input>
        </el-form-item>
        <el-checkbox v-model="checked" checked class="remember" style='display:none;'>记住密码</el-checkbox>
        <el-form-item style="width:100%;">
          <el-button type="primary" style="width:calc(50% - 7px);height:46px;" @click.native.prevent="handleSubmit2" :loading="logining">登录</el-button>
          <el-button @click="handleReset2" style="width:calc(50% - 7px);height:46px;">重置</el-button>
        </el-form-item>
    </div>
    <div style="display: inline-block;margin-left: 30px;">
  <img width="180px" height="180px" src='../assets/img/e9.jpg'>
  <h3 style="margin: 0;text-align: center;">欢迎使用微信扫码使用</h3></div>
  </el-form>
</template>

<script>
  import { requestLogin } from '../api/api';
  import axios from 'axios';
  //import NProgress from 'nprogress'
  export default {
    data() {
      var validaePass= (rule,value,callback) => {
          if(value&&value.length<4){
            callback(new Error('账号的长度不能小于4'));
          }else{
          callback();
          }

      }
      return {
        logining: false,
        ruleForm2: {
          account: 'admin',
          checkPass: '123456'
        },
        rules2: {
          account: [
            { required: true, message: '请输入账号', trigger: 'blur' },
            //{validator:function(rule,value,callback){
                //if(/^1[34578]\d{9}$/.test(value) == false){
                    //callback(new Error("请输入正确的手机号"));
                //}else{
                    //callback();
                //}
            //}, trigger: 'blur'}
            //{ min: 2,max:10, message: '长度在2到10个字符'}
            { validator: validaePass , trigger: 'blur'}
          ],
          checkPass: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            //{ validator: validaePass2 }
          ]
        },
        checked: true
      };
    },
    methods: {
      handleReset2() {
        this.$refs.ruleForm2.resetFields();
      },
      handleSubmit2(ev) {
        var _this = this;
        this.$refs.ruleForm2.validate((valid) => {
          if (valid) {
            //_this.$router.replace('/table');
            this.logining = true;
            //NProgress.start();
            var loginParams = { username: this.ruleForm2.account, pwd: this.ruleForm2.checkPass };
            requestLogin(loginParams).then(res => {
              this.logining = false;
              //NProgress.done();
              let { msg, state, data } = res;
              if (state !== 200) {
                this.$message({
                  message: msg,
                  type: 'error'
                });
              } else {
              	this.$message({
                  message: msg,
                  type: 'success'
                });
                sessionStorage.setItem('user', JSON.stringify(data));
                this.$router.push({ path: '/main' });
              }
            }).catch(e => {this.logining = false;console.log(e)});
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    }
  }

</script>
<style type="text/css">
  .login-container .el-input__inner{
    height:46px;
  }
  .login-container .el-form-item{
    margin-bottom:30px;
  }
  #app .el-menu i{
  	width: 14px;
    height: 14px;
    font-size: 14px;
  }
.sum_table.el-table{
    overflow: auto;
    border-right:1px solid #dfe6ec;
}
 .sum_table .el-table__header-wrapper, 
 .sum_table .el-table__body-wrapper, 
 .sum_table .el-table__footer-wrapper{
          overflow:visible;     
    }
/*  //这个是为了解决前面样式覆盖之后伪类带出来的竖线*/
 .sum_table.el-table::after{
    position: relative !important;
}
</style>
<style lang="scss" scoped>
  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 10px #cac6c6;
    .title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }
    .remember {
      margin: 0px 0px 35px 0px;
    }
  }

</style>