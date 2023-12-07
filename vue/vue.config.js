const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
//  chainWebpack: config => {
//   config.plugin(name: 'html')
//    .tap(f:args => {
//    args[0].title = "hello" ;
//    return args;}
//   })
// }
//   chainWebpack: config => {
//     config.plugin(name: 'html')
//       .tap(f:args =>{
//         args[0].title = "hello";
//         return args;
//     })
//   }
})
