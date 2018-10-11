package com.example.nnaija.tikcetstopper_kotlin.module.remoteDB

import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

abstract class  WebDbRequest {
     private lateinit var urlString:String

     init {
        urlString=WebDbGlobals.URLROOT+getScriptWebUrlString()
     }

      fun forwardWebDbRequest(objects: Array<Any>): Array<Any>? {

          var br: BufferedReader?=null
          var inS: InputStream?=null
          var bw:BufferedWriter?=null
          var out:OutputStream?=null


          try {
              val url:URL= URL(urlString)
              val httpConn=url.openConnection()as HttpURLConnection
              httpConn.doInput=true
              httpConn.doOutput = true
              httpConn.requestMethod = "POST"

              out = httpConn.outputStream
               bw = BufferedWriter(OutputStreamWriter(out, "utf-8"))


              bw.write(getUrlParamsString())
              bw.flush()
              bw.close()

              var sb = StringBuffer()
              inS = httpConn.inputStream
              br = BufferedReader(InputStreamReader(inS))

              BufferedReader(br).use { r->
                  r.lineSequence().forEach { sb.append(it+"\n") }
              }

              return parseRequestResult(sb)

          }catch (e:IOException){
              e.printStackTrace()

          }
          catch (e:Exception){
              e.printStackTrace()
          }
          catch (e:MalformedURLException){
              e.printStackTrace()
          }
          finally {
              if (out != null) {
                  out.close()
              }
              if (br != null) {
                  br.close()
              }
              if (bw != null) {
                  bw.close()
              }
              if (inS != null) {

                  inS.close()
              }
          }
            return null
     }

    private fun  getUrlParamsString():String{
        var UrlParams:String=""
        val map=getUrlParamsHashMap()
        var iteration=0
        map.forEach { key, value ->

            if (iteration!=0)UrlParams+="&"
            UrlParams=key+"="+URLEncoder.encode(value,"utf-8")
            iteration++
        }
        return UrlParams

    }

    /**
     * url=http//root/script
     * Provide url with String value of the remote script to call
     * If you need to change the url root, you can do so within WebDbGlobals
     */
     abstract fun getScriptWebUrlString():String

    /**
     * Provide HashMap to fill the url with parameters and their respective values
     */
     abstract  fun getUrlParamsHashMap():Map<String,String>

    /**
     * provide the way to handel the StringBuffer Result returned from http request
     */
    abstract fun parseRequestResult(sb:StringBuffer):Array<Any>

 }
