using System;
using System.Collections.Generic;
using WebShop.BL.BusinessLayerContracts;
using WebShop.BL.BusinessLayerContracts.DTOs;
using WebShop.SL.ServiceLayerContracts;

namespace WebShop.SL.ServiceLayerImpl
{
    class CategoryService : ICategoryService
    {
        private IUnitOfWork _uow;

        public CategoryService(IUnitOfWork uow)
        {
            _uow = uow;
        }

        public void Create(CategoryDTO categoryDTO)
        {
            _uow.CategoryOperations.Create(categoryDTO);
        }

        public IEnumerable<CategoryDTO> GetAll()
        {
            return _uow.CategoryOperations.Get();
        }
    }
}
