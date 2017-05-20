using System;
using System.Linq;

using Microsoft.VisualStudio.TestTools.UnitTesting;

using WebShop.DL.EFDataLayer;
using WebShop.SL.Client;
using WebShop.DL.DataLayerContract.Entities;
using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShopWCFService.Tests
{
    [TestClass]
    public class ProductServiceTests
    {
        [TestMethod]
        public void Create_ViaServiceCall_ProductGetsCreated()
        {
            // Arrange
            WebShop.DL.DataLayerContract.Entities.Category category = null;
            using (var ctx = new WebShopContext())
            {
                category = ctx.Categories.First(x => x.Name == "Category1");
            }

            var productName = string.Format("Test product name - {0}", DateTime.Now.Ticks);
            var client = new ProductClient();
            var product = new ProductDTO()
            {
                Name = productName,
                CategoryId = category.Id
            };

            // Act
            client.Create(product);

            // Assert
            using (var ctx = new WebShopContext())
            {
                var createdProduct = ctx.Products.First(x => x.Name == productName);
            }
        }
    }
}
