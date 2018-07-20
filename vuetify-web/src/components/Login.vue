<style lang="less">
    @import './login.less';
</style>

<template>
    <div class="login">
        <div class="login-con">
        <v-card>
            <v-card-title>
                <v-icon small>power_settings_new</v-icon>
                &nbsp;&nbsp;&nbsp;欢迎登录
            </v-card-title>
            <v-card-text> 
                <v-form ref="loginForm" lazy-validation>
                    <v-text-field label="用户名" required prepend-icon="account_box"></v-text-field>
                    <v-text-field label="密码" requiredprepend-icon="enhanced_encryption" type="password"></v-text-field>
                    <v-btn block color="info" dark @click="" >登录</v-btn>
                </v-form> 
            </v-card-text>     
        </v-card>
        </div>
    </div>
</template>

<script>

export default {
  data () {
    return {
    }
  },
  components: {
  },
  methods: {
  }
}
</script>

<style>

</style>