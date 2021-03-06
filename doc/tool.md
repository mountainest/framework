## windows
```shell
curl -H "Content-Type:application/json" -X POST -d 'json data' URL

# websocket
curl -i -N -H "Connection: Upgrade" -H "Upgrade: websocket"  https://test.com/test --http1.1 --output - -v

# 查看被占用端口对应的 PID
netstat -aon|findstr "8080"
# 结束指定pid的进程
taskkill /T /F /PID 9088

# 重启资源管理器
taskkill /f /im exp
方法1. 重启
下面两种方法不重启
方法2.  打开cmd，输入命令 set PATH=c，这个命令是使你写在path中的变量立即生效，然后重启cmd验证，补充（echo %PATH%可查看系统环境变量）
方法3. 注意即便系统环境变量生效了，若你之前打开的应用程序没关掉重启（在你没重启电脑的情况下），那你这个应用程序也可能读取不到该系统变量（哈哈，就是这么苟），所以你关掉重启该应用就好了
```

## wsl
```shell
# 1、以管理员身份进入命令行窗口，然后执行：
wsl --install
# 2、重启计算机
# 3、安装ubuntu。
wsl --install -d Ubuntu
```

#### 退出telnet：
1、按 ctrl + ]  
2、输入 quit

## k8s
```shell
# 使用-t指定镜像名称
docker build -t helloworld .
# 查看镜像列表
docker image ls
# 启动容易，可以通过 -p 指定端口映射，把容器内的80端口映射为宿主机的9001端口
docker run -p 9001:80 helloworld
```

## IDEA快捷键
清理无用的import：Ctrl + Alt + O

## git
```shell
# git config的作用范围由小到大：local(当前项目，默认) < global（当前用户） < system（所有用户，全局）
git config (--local 默认)
git config --global
git config --system
# 关闭ssl
git config http.sslVerify false
```