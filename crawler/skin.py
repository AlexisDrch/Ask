import urllib
import urllib.request
import json
import wget



# url = 'https://api.cognitive.microsoft.com/bing/v7.0/images/search?q="{}"'.format("Acne")

url= "https://api.cognitive.microsoft.com/bing/v7.0/images/search?q={q}&count={count}".format(q="clear skin", count= 100)

header= {'Ocp-Apim-Subscription-Key': "13ee79943f9440dcb6c0815ffc720a3e"}

response= requests.get(url, headers= header)
data= json.loads(response.content)


for images in data['value']:
  print(images['contentUrl'])
  try:
    wget.download(images['contentUrl'])
  except Exception:
    print("Err")