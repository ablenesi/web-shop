using System;
using System.Linq;

using Microsoft.VisualStudio.TestTools.UnitTesting;

using WebShop.DL.DataLayerContract.Entities;
using WebShop.DL.EFDataLayer;
using WebShop.IoC.DI;
using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerContracts.DTOs;

namespace WebShop.BL.BusinessLayerImpl.Tests
{
    [TestClass]
    public class ProductOperationsTests
    {
        [TestMethod]
        public void Create_NewProduct_SaveChangesIntoDB()
        {
            // Arrange
            Category category1 = null;
            var productName = string.Format("Test Product - {0}", DateTime.Now.Ticks);
            var price = 10;
            using (var ctx = new WebShopContext())
            {
                category1 = ctx.Categories.First(x => x.Name == "Category1");

                var product = new ProductDTO()
                {
                    CategoryId = category1.Id,
                    Name = productName,
                    Price = price
                };

                var productOperations = new ProductOperations(
                    new EFDataAccess<Product>(ctx),
                    new EFDataAccess<Category>(ctx));

                // Act
                productOperations.Create(product);
            }

            // Assert
            using (var ctx = new WebShopContext())
            {
                var savedProduct = ctx.Products.Single(x => x.Name == productName);
                Assert.AreEqual(price, savedProduct.Price, "The price is wrong.");
            }
        }

        [TestMethod]
        public void Create_NewProductUsingUnitOfWork_SaveChangesIntoDB()
        {
            // Arrange
            Category category1 = null;
            var productName = string.Format("Test Product - {0}", DateTime.Now.Ticks);
            var price = 10;
            using (var ctx = new WebShopContext())
            {
                category1 = ctx.Categories.First(x => x.Name == "Category1");

                var product = new ProductDTO()
                {
                    CategoryId = category1.Id,
                    Name = productName,
                    Price = price
                };

                var unitOfWork = new UnitOfWork(new EFRepository(ctx));

                // Act
                unitOfWork.ProductOperations.Create(product);
                unitOfWork.SaveChanges();
            }

            // Assert
            using (var ctx = new WebShopContext())
            {
                var savedProduct = ctx.Products.Single(x => x.Name == productName);
                Assert.AreEqual(price, savedProduct.Price, "The price is wrong.");
            }
        }

        [TestMethod]
        public void Create_NewProductUsingUnitOfWorkAndIoC_SaveChangesIntoDB()
        {
            // Arrange
            Category category = null;
            var productName = string.Format("Test Product - {0}", DateTime.Now.Ticks);
            var price = 10;
            using (var ctx = new WebShopContext())
            {
                category = ctx.Categories.First(x => x.Name == "Category1");
            }

            var product = new ProductDTO()
            {
                CategoryId = category.Id,
                Name = productName,
                Price = price
            };

            var unitOfWork = Resolver.Get<IUnitOfWork>();

            // Act
            unitOfWork.ProductOperations.Create(product);
            unitOfWork.SaveChanges();

            // Assert
            using (var ctx = new WebShopContext())
            {
                var savedProduct = ctx.Products.Single(x => x.Name == productName);
                Assert.AreEqual(price, savedProduct.Price, "The price is wrong.");
            }
        }
    }
}
