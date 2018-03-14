# -*- coding: utf-8 -*-
import scrapy
from amazon.items import AmazonItem

class AmazonproducctSpider(scrapy.Spider):
    name = 'AmazonProducct'
    allowed_domains = ['amazon.com']
    start_urls = [
     "https://www.amazon.com/Coleman-2000007827-Sundome-4-Person-Green/dp/B004J2GUOU/ref=sr_1_1_sspa?s=outdoor-recreation&ie=UTF8&qid=1520962649&sr=1-1-spons&keywords=tent&psc=1", "http://www.amazon.com/dp/B00JGTVU5A",
     "http://www.amazon.com/dp/B00O9A48N2", "http://www.amazon.com/dp/B00UZKG8QU",
     ]

    def parse(self, response):
        items = AmazonItem()
        title = response.xpath('//h1[@id="title"]/span/text()').extract()
        sale_price = response.xpath('//span[contains(@id,"ourprice")]')
        category = response.xpath('//a[@class="a-link-normal a-color-tertiary"]/text()').extract()
        
        items['name'] = ''.join(title).strip()
        items['avg_price'] = ''.join(sale_price).strip()
        items['description'] = ','.join(map(lambda x: x.strip(), category)).strip()
        yield items
        
        
        
        
        
