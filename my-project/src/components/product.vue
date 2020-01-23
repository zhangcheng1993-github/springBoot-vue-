<template>
    <div class="product-main" @click.stop="routerInfo(item.id)">
      <div class="product-main-item" :style="{backgroundColor:colors[item.color]}">
        <el-row  type="flex" justify="center" align="middle">
          <el-col :span="24">
            <div class="product-main-img">
              <img :src="require('@/assets/img/'+item.image)">
            </div>
            <div class="product-main-name">
              {{item.name}}
            </div>
            <div class="product-main-sales">
              ￥{{item.sales}}
            </div>
            <div class="product-main-cost">
              销量:{{item.cost}}
            </div>
          </el-col>
        </el-row>
        <div class="product-add" @click.stop="addProduct(item.id)">加入购物车</div>
      </div>
    </div>
</template>

<script>
    export default {
        name: "product",
        data(){
          return{
            colors:{
              '粉色':'#FFB6C1',
              '红色':'#DC143C',
              '金色':'#FFD700',
              '绿色':'#90EE90',
              '青色':'#00CED1',
            }
          }
        },
        props:{
          item:{
            type:Object
          }
        },
      methods:{
        addProduct(id){
          this.$message({
            message: '加入购物车成功!',
            showClose: true,
            type: 'success'
          });
          this.$store.commit('addCar',id);
        },
        routerInfo(id){
          this.$router.push({
            name:'productInfo',
            params:{id:id}
          });
        }
      }
    }
</script>

<style scoped>
  .product-main{
    height: 260px;
    width: 200px;
    cursor: pointer;
  }

  .product-main-img,img{
    height: 130px;
    width: 140px;
  }

  .product-main-name{
       width: 140px;
       height: 50px;
       margin: 5px auto;
  }

  .product-main-sales{
    width: 140px;
    height: 15px;
    margin: 10px auto;
  }

  .product-main-cost{
    width: 140px;
    height: 15px;
  }



  .product-main-item{
    text-align: center;
    height: 250px;
    width: 180px;
    display: flex;
    flex-flow:row  wrap;
    justify-content: center;
    align-items:center;
    position:relative;
  }

  .product-main :hover .product-add{
    display: inline-block;
  }

  .product-add{
    background-color: #9acfea;
    position:absolute;
    top: 1px;
    right: 2px;
    display: none;
  }

</style>
