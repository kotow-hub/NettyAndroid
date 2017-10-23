import com.goav.netty.Handler.ClientHelper
import com.goav.netty.Handler.ReConnect
import com.goav.netty.Impl.ChannelConnectImpl
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelOutboundHandlerAdapter
import io.netty.channel.ChannelPipeline
import io.netty.channel.ChannelPromise
import io.netty.channel.socket.SocketChannel
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val client = ClientHelper.init()
            .reConnect(ReConnect(true, 2))
            .address("0", 7373)
            .addCallBack(object : ChannelConnectImpl {
                override fun onConnectCallBack(sc: SocketChannel?) {
                    System.out.println((sc == null).toString())
                }

                override fun addChannelHandler(pipeline: ChannelPipeline): ChannelPipeline = pipeline.addLast(TestHandler()).addLast(OutChannel())
            })
            .build()
            .connect();
}


class TestHandler : io.netty.channel.ChannelDuplexHandler() {
    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        val value = String(msg as ByteArray)
        System.out.println(value)

        //        super.channelRead(ctx, msg)
    }

    override fun write(ctx: ChannelHandlerContext?, msg: Any?, promise: ChannelPromise?) {
        System.out.println("write")
        super.write(ctx, msg, promise)
    }

    override fun channelActive(ctx: ChannelHandlerContext?) {
        System.out.println("channelActive")
        ctx?.writeAndFlush("connectByKotlin")
        ctx?.executor()?.scheduleAtFixedRate(
                { ctx.writeAndFlush(SocketPong()) },
                0,
                10,
                TimeUnit.SECONDS)
        //        super.channelActive(ctx)
    }

    override fun channelRegistered(ctx: ChannelHandlerContext?) {
        System.out.println("channelRegistered")
        super.channelRegistered(ctx)
    }

    override fun channelUnregistered(ctx: ChannelHandlerContext?) {
        System.out.println("channelUnregistered")
        ctx?.close()
        //        super.channelUnregistered(ctx)
    }

    override fun channelInactive(ctx: ChannelHandlerContext?) {
        System.out.println("channelInactive")
        super.channelInactive(ctx)
    }

    override fun close(ctx: ChannelHandlerContext?, promise: ChannelPromise?) {
        System.out.println("close")
        super.close(ctx, promise)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        System.out.println(cause?.message)
        super.exceptionCaught(ctx, cause)
    }
}


class OutChannel : ChannelOutboundHandlerAdapter() {

    override fun write(ctx: ChannelHandlerContext?, msg: Any?, promise: ChannelPromise?) {
        System.out.print("write out")
        super.write(ctx, msg, promise)
    }


}