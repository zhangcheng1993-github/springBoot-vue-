<template>
    <div class="productInfo-main">
      <el-row  type="flex" justify="center" align="middle">
        <el-col :span="3"></el-col>
        <el-col :span="18">
            <el-container>
              <el-header>
                <el-alert title="商品详情" center  type="success" :closable="false"></el-alert>
              </el-header>
              <el-main>
                  <el-container>
                    <el-aside>
                        <img :src="require('@/assets/img/'+productInfoData.image)"></img>
                    </el-aside>
                    <el-main>
                        <div class="productInfo-main-item"><span>名称:{{productInfoData.name}}</span></div>
                        <div class="productInfo-main-item"><span>品牌{{productInfoData.brand}}</span></div>
                        <div class="productInfo-main-item"><span>价格:{{productInfoData.sales}}</span></div>
                        <div class="productInfo-main-item"><el-button @click="addCar(productInfoData.id)" type="warning" round>go</el-button></div>
                    </el-main>
                  </el-container>
              </el-main>
            </el-container>
        </el-col>
        <el-col :span="3"></el-col>
      </el-row>
    </div>
</template>

<script>
    export default {
        name: "productInfo",
        data(){
          return{
            productData:this.$store.state.shopStore.productList,
            id:this.$route.params.id
          }
        },
        computed:{
          productInfoData(){
            var _this=this;
            return  this.productData.find((item)=>item.id==_this.id);
          }
        },
        methods:{
          addCar(id){
            this.$store.commit('addCar',id);
            this.$message({
              message: '购买成功!',
              type: 'success',
              duration:500
            });
          }
        }

    }
</script>

<style scoped>
.productInfo-main-item{
  text-align: center;
  margin: 5px auto;
}
</style>
